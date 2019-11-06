import { Component, OnInit } from '@angular/core';
import { NavController, MenuController } from '@ionic/angular';
import { FirebaseConnectionService } from '../services/firebase-connection.service';
import { UserInternal } from '../interfaces/userInternal';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  errorMessage: string = '';
  private userInternal: UserInternal

  constructor(
    public navCtrl: NavController,
    public menu: MenuController,
    public firebase: FirebaseConnectionService,) {
    this.menu.enable(false);
  }

  loginUser() {
    this.firebase.loginUser(this.userInternal)
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
