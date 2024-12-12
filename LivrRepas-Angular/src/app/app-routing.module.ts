import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CartPageComponent } from './cart-page/cart-page.component';
import { FoodPageComponent } from './food-page/food-page.component';
import {HomeComponent} from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { FirstPageComponent } from './first-page/first-page.component';
import { ProfileComponent } from './profile/profile.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LivreurComponent } from './components/livreur/livreur.component';
import { RepasComponent } from './components/repas/repas.component';
import { SettingsComponent } from './components/settings/settings.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { ReviewsComponent } from './components/reviews/reviews.component';
import { WalletComponent } from './components/wallet/wallet.component';
import { LoginAdminComponent } from './components/login-admin/login-admin.component';

const routes: Routes = [
  {path:'', component:HomeComponent},
  // Partie Admin
  { path: 'admin/dashboard', component: DashboardComponent },
  { path: 'admin/livreur', component: LivreurComponent },
  {path:'admin/order-list', component:OrderListComponent},
  { path: 'admin/repas', component: RepasComponent },
  { path: 'admin/settings', component: SettingsComponent },
  {path:'admin/reviews', component:ReviewsComponent},
  {path:'admin/wallet', component:WalletComponent},
  {path:'admin/LoginAdmin', component:LoginAdminComponent},

  
  {path:'search/:searchTerm', component:HomeComponent},
  {path:'tag/:tag', component:HomeComponent},
  {path:'food/:id', component:FoodPageComponent},
  {path:'cart-page', component: CartPageComponent},
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'forgot-password', component: ForgetPasswordComponent },
  { path: 'first-page', component: FirstPageComponent },
  { path: 'profile', component: ProfileComponent }, 
  { path: '', redirectTo: '/login', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
