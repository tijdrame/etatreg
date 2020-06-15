import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { DechargementComponent } from './dechargement.component';
import { DechargementDetailComponent } from './dechargement-detail.component';
import { DechargementUpdateComponent } from './dechargement-update.component';
import { DechargementDeleteDialogComponent } from './dechargement-delete-dialog.component';
import { dechargementRoute } from './dechargement.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(dechargementRoute)],
  declarations: [DechargementComponent, DechargementDetailComponent, DechargementUpdateComponent, DechargementDeleteDialogComponent],
  entryComponents: [DechargementDeleteDialogComponent],
})
export class EtatregDechargementModule {}
