import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFilesInfos, FilesInfos } from 'app/shared/model/files-infos.model';
import { FilesInfosService } from './files-infos.service';
import { FilesInfosComponent } from './files-infos.component';
import { FilesInfosDetailComponent } from './files-infos-detail.component';
import { FilesInfosUpdateComponent } from './files-infos-update.component';

@Injectable({ providedIn: 'root' })
export class FilesInfosResolve implements Resolve<IFilesInfos> {
  constructor(private service: FilesInfosService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFilesInfos> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((filesInfos: HttpResponse<FilesInfos>) => {
          if (filesInfos.body) {
            return of(filesInfos.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FilesInfos());
  }
}

export const filesInfosRoute: Routes = [
  {
    path: '',
    component: FilesInfosComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'FilesInfos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FilesInfosDetailComponent,
    resolve: {
      filesInfos: FilesInfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FilesInfos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FilesInfosUpdateComponent,
    resolve: {
      filesInfos: FilesInfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FilesInfos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FilesInfosUpdateComponent,
    resolve: {
      filesInfos: FilesInfosResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'FilesInfos',
    },
    canActivate: [UserRouteAccessService],
  },
];
