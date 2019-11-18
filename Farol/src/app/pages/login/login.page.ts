import { Component, OnInit } from '@angular/core';
import { FirebaseConnectionService } from '../../services/firebase-connection.service';
import { UserInternal } from '../../interfaces/userInternal';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  errorMessage: string = '';
  private userLogin: UserInternal = {}

  constructor(        
    public service: FirebaseConnectionService,) {    
  }

  loginUser() {
    this.service.login(this.userLogin)          
  }
  ngOnInit() {
  }
}
