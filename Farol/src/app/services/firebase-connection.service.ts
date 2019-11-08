import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import { UserInternal } from '../interfaces/userInternal';
import { AngularFireAuth } from '@angular/fire/auth';
import { LoadingController, ToastController } from '@ionic/angular';

@Injectable({
  providedIn: 'root'
})
export class FirebaseConnectionService {  
  private loading: any
  private msgLoading : string

  constructor(
    private auth: AngularFireAuth,
    private firebaseFirestore: AngularFirestore,
    private loadingCtrl: LoadingController,
    private toastCtrl: ToastController
  ) { }

  // METODO PARA APRESENTAR O CARREGAMENTO (LOADING)
  async presentLoading(message : string) {
    this.loading = await this.loadingCtrl.create({
      message,
    })
    return this.loading.present()
  }
  // METODO PARA APRESENTAR ERROS NA TELA
  async presentToast(message: string) {
    const toast = await this.toastCtrl.create({
      message,
      duration: 3000
    });
    toast.present();
  }

  read_Students(){
    return this.firebaseFirestore.collection('users').snapshotChanges()
  }

  // Recebo um usuário da minha interface e registro passando o obejto
  async register(user : UserInternal, idioma : string) {
    this.msgLoading = "Registrando..."
    await this.presentLoading(this.msgLoading)
    // Aqui fiz uma validação meio na gambiarra pro idioma ele não registra se não colocar o idioma.
    if(idioma == null || idioma == ''){
      this.loading.dismiss()
      let idioma = 'Você precisa preencher o campo Idioma Nativo!'
      this.presentToast(idioma)
    }else{
      try {
        await this.auth.auth.createUserWithEmailAndPassword(user.email, user.password)
        this.sendParameters(user)
        console.log(this.auth.user)
      } catch (error) {
        console.log(error)
        let message: string     
        switch (error.code) {
          case 'auth/argument-error':
            message = 'Preencha os campos email e senha!'
            break
          case 'auth/email-already-in-use':
            message = 'Este e-mail já está sendo usado!'
            break
          case 'auth/invalid-email':
            message = 'O e-mail informado é inválido!'
            break
        }
        this.presentToast(message)
      } finally {
        this.loading.dismiss()
      }
    }
  }
  sendParameters(user : UserInternal) {    
    this.firebaseFirestore.collection('/users/').add(user)
  }
  async login(user: UserInternal) {
    this.msgLoading = "Efetuando Login..."
    await this.presentLoading(this.msgLoading)
    try {
      await this.auth.auth.signInWithEmailAndPassword(user.email, user.password)
    } catch (error) {
      console.log(error.code)
      let message = error.code
      switch (message) {
        case 'auth/argument-error':
          message = 'Preencha os campos email e senha!'
          break
        case 'auth/invalid-email':
          message = 'O email informado é inválido!'
          break
        case 'auth/wrong-password':
          message = 'A senha não confere!'
          break
        case 'auth/user-not-found':
          message = 'Usuário não localizado!'
          break
      }
      this.presentToast(message)
    } finally {
      this.loading.dismiss()
    }
  }
  async logout() {    
    this.msgLoading = "Efetuando Logout..."
    await this.presentLoading(this.msgLoading)
    this.loading.dismiss()
    this.auth.auth.signOut()      
  }
  getAuth() {
    return this.auth.auth
  }
}
