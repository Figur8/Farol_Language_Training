import { Component, OnInit } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { Message } from '../../interfaces/message'


@Component({
  selector: 'app-chat',
  templateUrl: './chat.page.html',
  styleUrls: ['./chat.page.scss'],
})
export class ChatPage implements OnInit {
      
  private message: Message = {}
  private texto: string
  private messages = new Array<Message>()
  private currentUser: any

  constructor(
    private auth: AngularFireAuth,
  ) { 
    this.auth.user.subscribe((data) => { this.currentUser = data, console.log(data) })       
  }
  
  ngOnInit() {
  }

  getMessage(){
    
  }

  sendMessage(){
    if(this.message !== ''){
      this.message.user = this.currentUser.displayName
      this.message.message = this.texto
      this.messages.push(this.message)    
      this.texto = null          
    }
  }

}
