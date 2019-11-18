import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { MenuPageModule } from './pages/menu/menu.module';

const routes: Routes = [
  {
    path: '',redirectTo: 'menu', pathMatch: 'full' },
  // { path: 'home', loadChildren: './pages/home/home.module#HomePageModule' },
  // { path: 'login', loadChildren: './pages/login/login.module#LoginPageModule' },
  // { path: 'register', loadChildren: './pages/register/register.module#RegisterPageModule' },
  // { path: 'chat', loadChildren: './pages/chat/chat.module#ChatPageModule' },
  { path: 'menu', loadChildren: './pages/menu/menu.module#MenuPageModule' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules }),
    MenuPageModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
