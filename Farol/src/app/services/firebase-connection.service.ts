import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import * as firebase from 'firebase/app';
import { UserInternal } from '../interfaces/userInternal';


export interface userFirebase{
  language: string;
  username: string;
  uuid: string;
}

@Injectable({
  providedIn: 'root'
})
export class FirebaseConnectionService {
  private user: Object;
  private uSerInfoImplements: firebase.UserInfo;
  private userInternal: UserInternal;

  constructor(
    private firestore: AngularFirestore
  ) { }

  read_Students() {
    return this.firestore.collection('users').snapshotChanges();
  }

  registerUser(userInternalInterfaceType: UserInternal){
    return new Promise<any>((resolve, reject) => {
      firebase.auth().createUserWithEmailAndPassword(userInternalInterfaceType.email, userInternalInterfaceType.password)
      .then(
        res => resolve(res),
        err => reject(err))
    }).then((confirm) => {
      this.uSerInfoImplements = confirm.user;
      console.log("ele é esse", this.uSerInfoImplements.uid);
      console.log("nome", userInternalInterfaceType.username, "lingua", userInternalInterfaceType.language);
      this.userInternal = {
        username: userInternalInterfaceType.username,
        language: userInternalInterfaceType.language,
        uuid: this.uSerInfoImplements.uid,
      }
      this.firestore.collection('/users/').add(userInternalInterfaceType);
    })
   }

   loginUser(userInternalInterfaceType: UserInternal){
    return new Promise<any>((resolve, reject) => {
      firebase.auth().signInWithEmailAndPassword(userInternalInterfaceType.email, userInternalInterfaceType.password)
      .then(
        res => resolve(res),
        err => reject(err))
    })
   }

   logoutUser(){
    return new Promise((resolve, reject) => {
      if(firebase.auth().currentUser){
        firebase.auth().signOut()
        .then(() => {
          console.log("LOG Out");
          resolve();
        }).catch((error) => {
          reject();
        });
      }
    })
  }

  userDetails(){
    return firebase.auth().currentUser;
  }
}
