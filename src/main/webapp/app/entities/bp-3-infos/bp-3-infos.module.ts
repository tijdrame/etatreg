import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { Bp3InfosComponent } from './bp-3-infos.component';
import { Bp3InfosDetailComponent } from './bp-3-infos-detail.component';
import { Bp3InfosUpdateComponent } from './bp-3-infos-update.component';
import { Bp3InfosDeleteDialogComponent } from './bp-3-infos-delete-dialog.component';
import { bp3InfosRoute } from './bp-3-infos.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bp3InfosRoute)],
  declarations: [Bp3InfosComponent, Bp3InfosDetailComponent, Bp3InfosUpdateComponent, Bp3InfosDeleteDialogComponent],
  entryComponents: [Bp3InfosDeleteDialogComponent],
})
export class EtatregBp3InfosModule {}
