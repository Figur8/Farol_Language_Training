//Classe que implementa uma interface para segurança das rotas
//Ou seja, se eu não tô logado não saio da page login. Se eu estou logado não retorno pra page login.
import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { FirebaseConnectionService } from '../services/firebase-connection.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: FirebaseConnectionService,
    private router: Router
  ){}

  //Retorno uma promise, que diz se o usuário tá logado ou não
  canActivate() : Promise<boolean> {
    return new Promise(resolve => {
      this.authService.getAuth().onAuthStateChanged(user => {
        if(!user) this.router.navigate(['login'])
        resolve(user ? true : false)
      })
    })
  }  
  
}
