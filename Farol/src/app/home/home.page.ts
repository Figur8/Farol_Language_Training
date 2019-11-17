import { Component, OnInit } from '@angular/core';
import { MenuController, NavController } from '@ionic/angular';
import { FirebaseConnectionService } from '../services/firebase-connection.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { User } from 'firebase';
@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {
 
  students: any;
  studentName: string;
  studentAge: number;
  studentAddress: string;
 
  constructor(
    private navCtrl: NavController, 
    private crudService: FirebaseConnectionService, 
    private menu: MenuController,
    private fbAuth: AngularFireAuth) { 
    this.menu.enable(true);
    }
 
  ngOnInit() {
    this.crudService.read_Students().subscribe(data => {
      this.students = data.map(e => {
        return {
          id: e.payload.doc.id,
          isEdit: false,
          username: e.payload.doc.data()['username'],
          language: e.payload.doc.data()['language']
          // Age: e.payload.doc.data()['Age'],
          // Address: e.payload.doc.data()['Address'],          
        };
      })      
      console.log(this.students)
      
    });
  }
 
  logout(){
    this.crudService.logout()
    this.navCtrl.navigateRoot('/login')
  }
 
}