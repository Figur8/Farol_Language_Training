import { Component, OnInit } from '@angular/core';
import { FirebaseConnectionService } from 'src/app/services/firebase-connection.service';
import { AngularFireAuth } from '@angular/fire/auth';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
})
export class MenuPage implements OnInit {

  public currentUser: any
  botoes = [
    {
      title: 'Dashboard',      
      icon: 'logo-google'
    },   
    {
      title: 'Feedback',      
      icon: 'logo-ionic'
    },   
    {
      title: 'Logout',            
      icon: 'home',      
    },
  ]

  constructor(
    private authService: FirebaseConnectionService,
    private auth: AngularFireAuth
  ) {   this.auth.user.subscribe((data) => { this.currentUser = data, console.log(data) }) }

  logout(){
    this.authService.logout()
  }
  ngOnInit() {
  }

}
