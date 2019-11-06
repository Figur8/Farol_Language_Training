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
    public firebase: FirebaseConnectionService,) {
  }

  register(): Promise<any> {
    return this.fbAuth.auth.createUserWithEmailAndPassword(this.userEmail, this.userPassword)
      .then((confirm) => {
        this.userInfoImplements = confirm.user;
        console.log("ele é esse", this.userInfoImplements.uid);
        console.log("nome", this.username, "lingua", this.language);
        this.userRegister = {
          language: this.username,
          username: this.language,
          uuid: this.userInfoImplements.uid
        }
        this.fireBaseStore.collection('/users/').add(this.userRegister);
      })
  }
  ngOnInit() {
  }

}
