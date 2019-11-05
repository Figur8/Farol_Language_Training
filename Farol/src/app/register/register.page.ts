import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth'
import { NavController } from '@ionic/angular'
import { auth, UserInfo } from 'firebase';

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
  
  private userEmail: string  
  private userPassword: string
  private userInfoImplements: UserInfo;

  constructor(
    private fbAuth: AngularFireAuth, 
    private nvCtrl: NavController) { }

  register(): Promise<any>{
    return this.fbAuth.auth.createUserWithEmailAndPassword(this.userEmail, this.userPassword)
    .then((confirm) => {
      this.userInfoImplements  = confirm.user;      
      console.log("ele é esse", this.userInfoImplements.uid)
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
