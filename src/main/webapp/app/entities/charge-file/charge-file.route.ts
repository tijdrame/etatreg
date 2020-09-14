import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IChargeFile, ChargeFile } from 'app/shared/model/charge-file.model';
import { ChargeFileService } from './charge-file.service';
import { ChargeFileComponent } from './charge-file.component';
import { ChargeFileDetailComponent } from './charge-file-detail.component';
import { ChargeFileUpdateComponent } from './charge-file-update.component';

@Injectable({ providedIn: 'root' })
export class ChargeFileResolve implements Resolve<IChargeFile> {
  constructor(private service: ChargeFileService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IChargeFile> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((chargeFile: HttpResponse<ChargeFile>) => {
          if (chargeFile.body) {
            return of(chargeFile.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ChargeFile());
  }
}

export const chargeFileRoute: Routes = [
  {
    path: '',
    component: ChargeFileComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'ChargeFiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ChargeFileDetailComponent,
    resolve: {
      chargeFile: ChargeFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ChargeFiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ChargeFileUpdateComponent,
    resolve: {
      chargeFile: ChargeFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ChargeFiles',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ChargeFileUpdateComponent,
    resolve: {
      chargeFile: ChargeFileResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ChargeFiles',
    },
    canActivate: [UserRouteAccessService],
  },
];
