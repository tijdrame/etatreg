import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BpdeviseComponent } from './bpdevise.component';
import { BpdeviseDetailComponent } from './bpdevise-detail.component';
import { BpdeviseUpdateComponent } from './bpdevise-update.component';
import { BpdeviseDeleteDialogComponent } from './bpdevise-delete-dialog.component';
import { bpdeviseRoute } from './bpdevise.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bpdeviseRoute)],
  declarations: [BpdeviseComponent, BpdeviseDetailComponent, BpdeviseUpdateComponent, BpdeviseDeleteDialogComponent],
  entryComponents: [BpdeviseDeleteDialogComponent],
})
export class EtatregBpdeviseModule {}
