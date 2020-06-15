import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { ChargementComponent } from './chargement.component';
import { ChargementDetailComponent } from './chargement-detail.component';
import { ChargementUpdateComponent } from './chargement-update.component';
import { ChargementDeleteDialogComponent } from './chargement-delete-dialog.component';
import { chargementRoute } from './chargement.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(chargementRoute)],
  declarations: [ChargementComponent, ChargementDetailComponent, ChargementUpdateComponent, ChargementDeleteDialogComponent],
  entryComponents: [ChargementDeleteDialogComponent],
})
export class EtatregChargementModule {}
