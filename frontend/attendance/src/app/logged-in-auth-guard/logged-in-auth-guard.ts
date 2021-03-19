import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {LogInService} from '../services/log-in.service';

@Injectable()
export class LoggedInAuthGuard implements CanActivate {

  constructor(private _authService: LogInService, private _router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this._authService.isLoggedIn()) {
      this._router.navigate(['/']);
      return false;
    } else {
      return true;
    }
  }
}


