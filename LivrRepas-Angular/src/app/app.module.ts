import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { RatingModule } from 'ng-starrating';
import { SearchComponent } from './search/search.component';
import { FormsModule } from '@angular/forms';
import { TagsComponent } from './tags/tags.component';
import { FoodPageComponent } from './food-page/food-page.component';
import { CartPageComponent } from './cart-page/cart-page.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { RegisterComponent } from './register/register.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { FirstPageComponent } from './first-page/first-page.component';
import { FoodFilterComponent } from './food-filter/food-filter.component';
import { ProfileComponent } from './profile/profile.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LivreurComponent } from './components/livreur/livreur.component';
import { RepasComponent } from './components/repas/repas.component';
import { SettingsComponent } from './components/settings/settings.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { ReviewsComponent } from './components/reviews/reviews.component';
import { WalletComponent } from './components/wallet/wallet.component';
import { LoginAdminComponent } from './components/login-admin/login-admin.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    SearchComponent,
    TagsComponent,
    FoodPageComponent,
    CartPageComponent,
    NotFoundComponent,
    RegisterComponent,
    ForgetPasswordComponent,
    FirstPageComponent,
    FoodFilterComponent,
    ProfileComponent,
    DashboardComponent,
    LivreurComponent,
    RepasComponent,
    SettingsComponent,
    SidebarComponent,
    OrderListComponent,
    ReviewsComponent,
    WalletComponent,
    LoginAdminComponent,
    LoginComponent

  ],
  imports: [
     
    BrowserModule,
    AppRoutingModule,
    RatingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
