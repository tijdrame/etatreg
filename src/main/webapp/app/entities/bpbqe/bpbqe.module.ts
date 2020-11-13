import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BpbqeComponent } from './bpbqe.component';
import { BpbqeDetailComponent } from './bpbqe-detail.component';
import { BpbqeUpdateComponent } from './bpbqe-update.component';
import { BpbqeDeleteDialogComponent } from './bpbqe-delete-dialog.component';
import { bpbqeRoute } from './bpbqe.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bpbqeRoute)],
  declarations: [BpbqeComponent, BpbqeDetailComponent, BpbqeUpdateComponent, BpbqeDeleteDialogComponent],
  entryComponents: [BpbqeDeleteDialogComponent],
})
export class EtatregBpbqeModule {}
