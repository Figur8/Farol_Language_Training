import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';

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
