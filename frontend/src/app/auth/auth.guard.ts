import { inject } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";


export const AuthGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    const router: Router = inject(Router);
    const snack: MatSnackBar = inject(MatSnackBar);
    const token = localStorage.getItem('token');

    if (token == null || token == '') {
        snack.open("Você não tem permissão", "X", {duration: 5000});
        router.navigate(['/login']);
        return false;
    }

    snack.open("Login feito com sucesso.", "X", {duration: 4000});
    return true;
}