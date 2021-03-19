import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {LogInService} from '../services/log-in.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private _authService: LogInService, private _router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this._authService.isLoggedIn()) {
      return true;
    } else {
      this._router.navigate(['/login']);
      return false;
    }
  }

}
