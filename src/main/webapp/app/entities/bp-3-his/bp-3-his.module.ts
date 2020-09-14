import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { Bp3HisComponent } from './bp-3-his.component';
import { Bp3HisDetailComponent } from './bp-3-his-detail.component';
import { Bp3HisUpdateComponent } from './bp-3-his-update.component';
import { Bp3HisDeleteDialogComponent } from './bp-3-his-delete-dialog.component';
import { bp3HisRoute } from './bp-3-his.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bp3HisRoute)],
  declarations: [Bp3HisComponent, Bp3HisDetailComponent, Bp3HisUpdateComponent, Bp3HisDeleteDialogComponent],
  entryComponents: [Bp3HisDeleteDialogComponent],
})
export class EtatregBp3HisModule {}
