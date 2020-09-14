import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { Bp1HisComponent } from './bp-1-his.component';
import { Bp1HisDetailComponent } from './bp-1-his-detail.component';
import { Bp1HisUpdateComponent } from './bp-1-his-update.component';
import { Bp1HisDeleteDialogComponent } from './bp-1-his-delete-dialog.component';
import { bp1HisRoute } from './bp-1-his.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bp1HisRoute)],
  declarations: [Bp1HisComponent, Bp1HisDetailComponent, Bp1HisUpdateComponent, Bp1HisDeleteDialogComponent],
  entryComponents: [Bp1HisDeleteDialogComponent],
})
export class EtatregBp1HisModule {}
