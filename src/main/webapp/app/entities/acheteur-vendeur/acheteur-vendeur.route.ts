import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAcheteurVendeur, AcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';
import { AcheteurVendeurService } from './acheteur-vendeur.service';
import { AcheteurVendeurComponent } from './acheteur-vendeur.component';
import { AcheteurVendeurDetailComponent } from './acheteur-vendeur-detail.component';
import { AcheteurVendeurUpdateComponent } from './acheteur-vendeur-update.component';

@Injectable({ providedIn: 'root' })
export class AcheteurVendeurResolve implements Resolve<IAcheteurVendeur> {
  constructor(private service: AcheteurVendeurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAcheteurVendeur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((acheteurVendeur: HttpResponse<AcheteurVendeur>) => {
          if (acheteurVendeur.body) {
            return of(acheteurVendeur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AcheteurVendeur());
  }
}

export const acheteurVendeurRoute: Routes = [
  {
    path: '',
    component: AcheteurVendeurComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'AcheteurVendeurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AcheteurVendeurDetailComponent,
    resolve: {
      acheteurVendeur: AcheteurVendeurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'AcheteurVendeurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AcheteurVendeurUpdateComponent,
    resolve: {
      acheteurVendeur: AcheteurVendeurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'AcheteurVendeurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AcheteurVendeurUpdateComponent,
    resolve: {
      acheteurVendeur: AcheteurVendeurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'AcheteurVendeurs',
    },
    canActivate: [UserRouteAccessService],
  },
];
