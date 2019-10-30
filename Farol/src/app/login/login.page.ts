import { Component, OnInit } from '@angular/core';
import { NavController, MenuController } from '@ionic/angular';
import { FirebaseAuth } from '@angular/fire';
import { FirebaseConnectionService } from '../services/firebase-connection.service';
// import { FirebaseConnectionService } from '../services/firebase-connection.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  errorMessage: string = '';
  private user: string;
  private password: string;

  constructor(public navCtrl: NavController, public menu: MenuController, public firebase: FirebaseConnectionService) { 
    this.menu.enable(false);
  }

  loginUser(){   
    this.firebase.loginUser(this.user, this.password)    
    .then(res => {
      console.log(res);
      this.errorMessage = "";
      this.navCtrl.navigateRoot('/home');
      this.firebase.userDetails();
    }, err => {
      this.errorMessage = err.message;
      // alert(this.errorMessage);      
      alert('Usuário ou Senha inválidos')
    })    
  }
  ngOnInit() {
  }

}
