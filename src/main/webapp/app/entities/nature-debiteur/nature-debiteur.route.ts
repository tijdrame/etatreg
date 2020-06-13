import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INatureDebiteur, NatureDebiteur } from 'app/shared/model/nature-debiteur.model';
import { NatureDebiteurService } from './nature-debiteur.service';
import { NatureDebiteurComponent } from './nature-debiteur.component';
import { NatureDebiteurDetailComponent } from './nature-debiteur-detail.component';
import { NatureDebiteurUpdateComponent } from './nature-debiteur-update.component';

@Injectable({ providedIn: 'root' })
export class NatureDebiteurResolve implements Resolve<INatureDebiteur> {
  constructor(private service: NatureDebiteurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INatureDebiteur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((natureDebiteur: HttpResponse<NatureDebiteur>) => {
          if (natureDebiteur.body) {
            return of(natureDebiteur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NatureDebiteur());
  }
}

export const natureDebiteurRoute: Routes = [
  {
    path: '',
    component: NatureDebiteurComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'NatureDebiteurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NatureDebiteurDetailComponent,
    resolve: {
      natureDebiteur: NatureDebiteurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'NatureDebiteurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NatureDebiteurUpdateComponent,
    resolve: {
      natureDebiteur: NatureDebiteurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'NatureDebiteurs',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NatureDebiteurUpdateComponent,
    resolve: {
      natureDebiteur: NatureDebiteurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'NatureDebiteurs',
    },
    canActivate: [UserRouteAccessService],
  },
];
