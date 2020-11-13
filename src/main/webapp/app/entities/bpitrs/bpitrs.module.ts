import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BpitrsComponent } from './bpitrs.component';
import { BpitrsDetailComponent } from './bpitrs-detail.component';
import { BpitrsUpdateComponent } from './bpitrs-update.component';
import { BpitrsDeleteDialogComponent } from './bpitrs-delete-dialog.component';
import { bpitrsRoute } from './bpitrs.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bpitrsRoute)],
  declarations: [BpitrsComponent, BpitrsDetailComponent, BpitrsUpdateComponent, BpitrsDeleteDialogComponent],
  entryComponents: [BpitrsDeleteDialogComponent],
})
export class EtatregBpitrsModule {}
