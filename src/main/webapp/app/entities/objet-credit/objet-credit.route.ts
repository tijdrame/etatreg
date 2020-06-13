import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IObjetCredit, ObjetCredit } from 'app/shared/model/objet-credit.model';
import { ObjetCreditService } from './objet-credit.service';
import { ObjetCreditComponent } from './objet-credit.component';
import { ObjetCreditDetailComponent } from './objet-credit-detail.component';
import { ObjetCreditUpdateComponent } from './objet-credit-update.component';

@Injectable({ providedIn: 'root' })
export class ObjetCreditResolve implements Resolve<IObjetCredit> {
  constructor(private service: ObjetCreditService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IObjetCredit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((objetCredit: HttpResponse<ObjetCredit>) => {
          if (objetCredit.body) {
            return of(objetCredit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ObjetCredit());
  }
}

export const objetCreditRoute: Routes = [
  {
    path: '',
    component: ObjetCreditComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ObjetCredits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ObjetCreditDetailComponent,
    resolve: {
      objetCredit: ObjetCreditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ObjetCredits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ObjetCreditUpdateComponent,
    resolve: {
      objetCredit: ObjetCreditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ObjetCredits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ObjetCreditUpdateComponent,
    resolve: {
      objetCredit: ObjetCreditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ObjetCredits',
    },
    canActivate: [UserRouteAccessService],
  },
];
