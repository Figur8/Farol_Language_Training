import { Component, OnInit } from '@angular/core';
import { FirebaseConnectionService } from '../services/firebase-connection.service';
import { UserInternal } from '../interfaces/userInternal';
import { MenuController, NavController } from '@ionic/angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  errorMessage: string = '';
  private userLogin: UserInternal = {}

  constructor(    
    public menu: MenuController,
    private navCtrl: NavController,
    public firebase: FirebaseConnectionService,) {
    this.menu.enable(false);
  }

  loginUser() {
    this.firebase.login(this.userLogin)
    this.navCtrl.navigateRoot('/home')
  }
  ngOnInit() {
  }
}
