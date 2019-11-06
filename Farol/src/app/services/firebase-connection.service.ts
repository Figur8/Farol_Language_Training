import { Injectable } from '@angular/core';
import { AngularFirestore } from '@angular/fire/firestore';
import * as firebase from 'firebase/app';


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

  constructor(
    private firestore: AngularFirestore
  ) { }

  read_Students() {
    return this.firestore.collection('users').snapshotChanges();
  }

  registerUser(userInternalInterfaceType){
    return new Promise<any>((resolve, reject) => {
      firebase.auth().createUserWithEmailAndPassword(userInternalInterfaceType.email, userInternalInterfaceType.password)
      .then(
        res => resolve(res),
        err => reject(err))
    }).then(()=>{
      this.uSerInfoImplements = this.userDetails();
      console.log(this.uSerInfoImplements.uid);
    })
   }

   loginUser(userInternalInterfaceType){
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
