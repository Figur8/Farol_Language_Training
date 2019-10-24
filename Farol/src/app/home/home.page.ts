import { Component, OnInit } from '@angular/core';
 
import { FirebaseConnectionService } from '../services/firebase-connection.service';
//  https://www.freakyjolly.com/ionic-4-crud-operations-using-firebase-and-firestore-database-tutorial-in-ionic-4-with-angular-7/
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
 
  constructor(private crudService: FirebaseConnectionService) { }
 
  ngOnInit() {
    this.crudService.read_Students().subscribe(data => {
 
      this.students = data.map(e => {
        return {
          id: e.payload.doc.id,
          isEdit: false,
          Name: e.payload.doc.data()['Name'],
          Age: e.payload.doc.data()['Age'],
          Address: e.payload.doc.data()['Address'],
        };
      })
      console.log(this.students);
 
    });
  }
 
 
}