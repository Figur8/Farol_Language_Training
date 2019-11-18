import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { NavParams, ToastController } from '@ionic/angular'
import { Observable } from 'rxjs/internal/Observable';
import { UserInternal } from 'src/app/interfaces/userInternal';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.page.html',
  styleUrls: ['./chat.page.scss'],
})
export class ChatPage implements OnInit {
    
  private message: string
  private messages = new Array<any>()  
  private currentUser: any

  constructor(
    private auth: AngularFireAuth,
  ) { 
    this.auth.user.subscribe((data) => { this.currentUser = data, console.log(data) })       
  }
  
  ngOnInit() {
  }

}
