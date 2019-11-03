import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth'
import { NavController } from '@ionic/angular'

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  
  private userEmail: string
  private userPassword: string

  constructor(
    private fbAuth: AngularFireAuth, 
    private nvCtrl: NavController) { }

  register(){
    try {
      if(this.fbAuth.auth.createUserWithEmailAndPassword(this.userEmail, this.userPassword))
      console.log('cadastro efetuado')
      this.nvCtrl.navigateRoot('/login')
    } catch (error) {
      console.log(error.message)
      
    }
      
  }

  ngOnInit() {
  }

}
