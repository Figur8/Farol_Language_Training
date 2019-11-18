import { Component, OnInit } from '@angular/core';
import { FirebaseConnectionService } from '../../services/firebase-connection.service';
import { UserInternal } from '../../interfaces/userInternal';
import { MenuController, NavController } from '@ionic/angular';
import { AngularFireAuth } from '@angular/fire/auth';

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
    public service: FirebaseConnectionService,) {
    this.menu.enable(false);
  }

  loginUser() {
    this.service.login(this.userLogin)          
  }
  ngOnInit() {
  }
}
