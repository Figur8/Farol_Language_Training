import { Component, OnInit } from '@angular/core';
import { NavController, MenuController } from '@ionic/angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor(public navCtrl: NavController, public menu: MenuController) { 
    this.menu.enable(false);
  }

  logar(){
    let user = (<HTMLSelectElement>document.querySelector('#user'))
    let pass = (<HTMLSelectElement>document.querySelector('#pass'))
    let userLogin = "admin"
    let userPass = "admin"

    if(user.value === userLogin && pass.value === userPass){
      user.value = ''
      pass.value = ''
      this.navCtrl.navigateForward('/home')      
    }else{
      alert("Login ou senha inválidos")
    }
  }
  ngOnInit() {
  }

}
