import { Component, OnInit } from '@angular/core';
import { FirebaseConnectionService } from 'src/app/services/firebase-connection.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.page.html',
  styleUrls: ['./menu.page.scss'],
})
export class MenuPage implements OnInit {

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
    private authService: FirebaseConnectionService
  ) { }

  logout(){
    this.authService.logout()
  }
  ngOnInit() {
  }

}
