import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { MenuPage } from './menu.page';
import { AuthGuard } from 'src/app/guards/auth.guard';
import { LoginGuard } from 'src/app/guards/login.guard';

const routes: Routes = [
  { path: '', redirectTo:'/menu/login', pathMatch: 'full' },
  { path: '', component: MenuPage, children: [
      { path: 'home', loadChildren: '../home/home.module#HomePageModule', canActivate: [AuthGuard]},
      { path: 'login', loadChildren: '../login/login.module#LoginPageModule', canActivate: [LoginGuard] },
      { path: 'register', loadChildren: '../register/register.module#RegisterPageModule' },
      { path: 'chat', loadChildren: '../chat/chat.module#ChatPageModule' },
    ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [MenuPage]
})
export class MenuPageModule {}
