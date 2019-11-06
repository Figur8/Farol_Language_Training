import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { NavController } from '@ionic/angular';
import { auth, UserInfo } from 'firebase';
import { AngularFirestore } from '@angular/fire/firestore';
import { UserInternal } from '../interfaces/userInternal';
import { FirebaseConnectionService } from '../services/firebase-connection.service';

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
  private userRegister: UserInternal;

  constructor(
    private fbAuth: AngularFireAuth,
    private fireBaseStore: AngularFirestore,
    public firebase: FirebaseConnectionService, ) {
  }

  register(){
    this.userRegister = {
      username: this.username,
      email: this.userEmail,
      password: this.userPassword,
      language: this.language
    }
    this.firebase.registerUser(this.userRegister);
  }
  ngOnInit() {
  }

}
