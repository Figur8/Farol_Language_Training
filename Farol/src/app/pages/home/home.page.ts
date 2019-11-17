import { Component, OnInit } from '@angular/core';
import { MenuController, NavController } from '@ionic/angular';
import { FirebaseConnectionService } from '../../services/firebase-connection.service';
import { AngularFirestoreCollection, AngularFirestore } from '@angular/fire/firestore';
import { UserInternal } from 'src/app/interfaces/userInternal';
import { map } from 'rxjs/operators'
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {  
  
  public idiomas = ['', 'Inglês', 'Português', 'Espanhol', 'Alemão', 'Francês', 'Chinês', 'Russo', 'Latin']

  constructor(
    private navCtrl: NavController, 
    private crudService: FirebaseConnectionService, 
    private menu: MenuController,
    private angularFS: AngularFirestore) { 
      this.menu.enable(true);                  
    }
 
  ngOnInit() {                    
  }  
   
  logout(){
    this.crudService.logout()
    this.navCtrl.navigateRoot('/login')
  }
 
}