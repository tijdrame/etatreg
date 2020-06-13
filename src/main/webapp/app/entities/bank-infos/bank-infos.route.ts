import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBankInfos, BankInfos } from 'app/shared/model/bank-infos.model';
import { BankInfosService } from './bank-infos.service';
import { BankInfosComponent } from './bank-infos.component';
import { BankInfosDetailComponent } from './bank-infos-detail.component';
import { BankInfosUpdateComponent } from './bank-infos-update.component';

@Injectable({ providedIn: 'root' })
export class BankInfosResolve implements Resolve<IBankInfos> {
  constructor(private service: BankInfosService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBankInfos> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bankInfos: HttpResponse<BankInfos>) => {
          if (bankInfos.body) {
            return of(bankInfos.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BankInfos());
  }
}

export const bankInfosRoute: Routes = [
  {
    path: '',
    component: BankInfosComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'BankInfos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BankInfosDetailComponent,
    resolve: {
      bankInfos: BankInfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BankInfos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BankInfosUpdateComponent,
    resolve: {
      bankInfos: BankInfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BankInfos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BankInfosUpdateComponent,
    resolve: {
      bankInfos: BankInfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BankInfos',
    },
    canActivate: [UserRouteAccessService],
  },
];
