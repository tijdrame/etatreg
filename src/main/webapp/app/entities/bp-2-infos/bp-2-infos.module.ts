import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { Bp2InfosComponent } from './bp-2-infos.component';
import { Bp2InfosDetailComponent } from './bp-2-infos-detail.component';
import { Bp2InfosUpdateComponent } from './bp-2-infos-update.component';
import { Bp2InfosDeleteDialogComponent } from './bp-2-infos-delete-dialog.component';
import { bp2InfosRoute } from './bp-2-infos.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bp2InfosRoute)],
  declarations: [Bp2InfosComponent, Bp2InfosDetailComponent, Bp2InfosUpdateComponent, Bp2InfosDeleteDialogComponent],
  entryComponents: [Bp2InfosDeleteDialogComponent],
})
export class EtatregBp2InfosModule {}
