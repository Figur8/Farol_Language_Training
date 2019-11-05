import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { NavController } from '@ionic/angular';
import { auth, UserInfo } from 'firebase';
import { AngularFirestore } from '@angular/fire/firestore';

export interface userFirebase{
  language: string;
  username: string;
  uuid: string;
}

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  
  private userEmail: string;  
  private userPassword: string;
  private username: string;
  private language: string;
  private userInfoImplements: UserInfo;
  private userInternalFireBase: userFirebase;
  
  constructor(
    private fbAuth: AngularFireAuth, 
    private nvCtrl: NavController,
    private fireBaseStore: AngularFirestore) {

      
    }
    
  register(): Promise<any>{
    return this.fbAuth.auth.createUserWithEmailAndPassword(this.userEmail, this.userPassword)
    .then((confirm) => {
      this.userInfoImplements  = confirm.user;      
      console.log("ele é esse", this.userInfoImplements.uid);
      console.log("nome", this.username, "lingua", this.language);
      this.userInternalFireBase = {
        username: this.language,
        language: this.username,
        uuid: this.userInfoImplements.uid
      }
      this.fireBaseStore.collection('/users/').add(this.userInternalFireBase);
    })


    // try {
    //   if(this.fbAuth.auth.createUserWithEmailAndPassword(this.userEmail, this.userPassword))
    //   console.log('cadastro efetuado')
    //   this.nvCtrl.navigateRoot('/login')
    // } catch (error) {
    //   console.log(error.message)
      
    // }
      
  }

  ngOnInit() {
  }

}
