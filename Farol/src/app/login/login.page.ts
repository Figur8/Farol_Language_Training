import { Component, OnInit } from '@angular/core';
import { NavController, MenuController } from '@ionic/angular';
import { FirebaseConnectionService } from '../services/firebase-connection.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  errorMessage: string = '';
  private user: string;
  private password: string;

  constructor(public navCtrl: NavController, public menu: MenuController, public firebaseService: FirebaseConnectionService) { 
    this.menu.enable(false);
  }

  loginUser(){
    this.firebaseService.loginUser(this.user, this.password)
    .then(res => {
      console.log(res);
      this.errorMessage = "";
      this.navCtrl.navigateRoot('/home');
      this.firebaseService.userDetails();
    }, err => {
      this.errorMessage = err.message;
      alert(this.errorMessage);
    })
  }
  ngOnInit() {
  }
}
