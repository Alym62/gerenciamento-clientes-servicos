import { ActivatedRouteSnapshot, CanActivateFn, RouterStateSnapshot } from "@angular/router";


export const AuthGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    const token = localStorage.getItem('token');

    if (token !== null) {
        return true
    }

    return true;
}