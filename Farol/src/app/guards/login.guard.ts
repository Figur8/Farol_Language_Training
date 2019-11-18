import { Injectable } from '@angular/core';
import { FirebaseConnectionService } from '../services/firebase-connection.service';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {
  constructor(
    private authService: FirebaseConnectionService,
    private router: Router
  ){}

  canActivate(): Promise<boolean>{
    return new Promise(resolve => {
      this.authService.getAuth().onAuthStateChanged(user => {
        if(user) this.router.navigate(['home'])
        resolve(!user ? true : false)
      })
    })
  }
  
}
