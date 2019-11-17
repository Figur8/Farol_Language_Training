import { Component, OnInit } from '@angular/core';
import { FirebaseConnectionService } from '../../services/firebase-connection.service';
import { UserInternal } from '../../interfaces/userInternal';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {    
  private userRegister: UserInternal = {}

  constructor(    
    public firebase: FirebaseConnectionService, ) {
  }

  register(){   
    this.firebase.register(this.userRegister);
  }
  ngOnInit() {
  }

}
