import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserLoginComponent } from './user-login/user-login.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { NewUserComponent } from './new-user/new-user.component';
import { HomeComponent } from './home/home.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { UpdateComponent } from './update/update.component';
import { CitizenListComponent } from './citizen-list/citizen-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: UserLoginComponent },
  { path: 'admin-login', component: AdminLoginComponent },
  { path: 'new-user', component: NewUserComponent },
  { path: 'home', component: HomeComponent },
  { path: 'admin-home', component: AdminHomeComponent },
  { path: 'update', component: UpdateComponent },
  { path: 'citizenlist', component: CitizenListComponent }
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
