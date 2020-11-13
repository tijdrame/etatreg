import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BpnaemaComponent } from './bpnaema.component';
import { BpnaemaDetailComponent } from './bpnaema-detail.component';
import { BpnaemaUpdateComponent } from './bpnaema-update.component';
import { BpnaemaDeleteDialogComponent } from './bpnaema-delete-dialog.component';
import { bpnaemaRoute } from './bpnaema.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bpnaemaRoute)],
  declarations: [BpnaemaComponent, BpnaemaDetailComponent, BpnaemaUpdateComponent, BpnaemaDeleteDialogComponent],
  entryComponents: [BpnaemaDeleteDialogComponent],
})
export class EtatregBpnaemaModule {}
