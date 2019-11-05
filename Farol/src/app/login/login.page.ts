import { Component, OnInit } from '@angular/core';
import { NavController, MenuController } from '@ionic/angular';
import { FirebaseConnectionService } from '../services/firebase-connection.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { UserInfo } from 'firebase';
// import { FirebaseConnectionService } from '../services/firebase-connection.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  errorMessage: string = '';
  private user: string;
  private credentials: JSON;
  private password: string;
  

  constructor(
    public navCtrl: NavController, 
    public menu: MenuController, 
    public firebase: FirebaseConnectionService,
    public fbAuth: AngularFireAuth) { 
    this.menu.enable(false);        
       
  }
  
  loginUser(){   
    this.firebase.loginUser(this.user, this.password)    
    .then(res => {
      console.log(res);
      this.errorMessage = "";
      this.navCtrl.navigateRoot('/home');
      
    }, err => {
      this.errorMessage = err.message;
      // alert(this.errorMessage);      
      alert('Usuário ou Senha inválidos')
    })    
  }
  ngOnInit() {
  }
}
