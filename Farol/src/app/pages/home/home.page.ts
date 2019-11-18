import { Component, OnInit } from '@angular/core';
import { MenuController } from '@ionic/angular';
import { FirebaseConnectionService } from '../../services/firebase-connection.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { UserInternal } from 'src/app/interfaces/userInternal';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage implements OnInit {  

  
  public idiomas = ['', 'Inglês', 'Português', 'Espanhol', 'Alemão', 'Francês', 'Chinês', 'Russo', 'Latin']   
  private users = new Array<UserInternal>()
  private currentUser: any  
  private userSubscription: Subscription  

  constructor(
    private service: FirebaseConnectionService, 
    private menu: MenuController,    
    private auth: AngularFireAuth) { 
      this.menu.enable(true);  
      this.userSubscription = this.service.getAll().subscribe(data => { this.users = data })    
      this.auth.user.subscribe((data) => { this.currentUser = data, console.log(data) })                
    }
 
  openMenu(){
    document.querySelector('ion-menu-controller')
    .open()
  }
  ngOnInit() {                   
  }  
  
  ionViewWillLeave(){
    this.userSubscription.unsubscribe()
  }
   
  logout(){
    this.service.logout()    
  }
}