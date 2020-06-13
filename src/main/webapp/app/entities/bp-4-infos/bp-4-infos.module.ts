import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { Bp4InfosComponent } from './bp-4-infos.component';
import { Bp4InfosDetailComponent } from './bp-4-infos-detail.component';
import { Bp4InfosUpdateComponent } from './bp-4-infos-update.component';
import { Bp4InfosDeleteDialogComponent } from './bp-4-infos-delete-dialog.component';
import { bp4InfosRoute } from './bp-4-infos.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bp4InfosRoute)],
  declarations: [Bp4InfosComponent, Bp4InfosDetailComponent, Bp4InfosUpdateComponent, Bp4InfosDeleteDialogComponent],
  entryComponents: [Bp4InfosDeleteDialogComponent],
})
export class EtatregBp4InfosModule {}
