import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { Bp1ComComponent } from './bp-1-com.component';
import { Bp1ComDetailComponent } from './bp-1-com-detail.component';
import { Bp1ComUpdateComponent } from './bp-1-com-update.component';
import { Bp1ComDeleteDialogComponent } from './bp-1-com-delete-dialog.component';
import { bp1ComRoute } from './bp-1-com.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bp1ComRoute)],
  declarations: [Bp1ComComponent, Bp1ComDetailComponent, Bp1ComUpdateComponent, Bp1ComDeleteDialogComponent],
  entryComponents: [Bp1ComDeleteDialogComponent],
})
export class EtatregBp1ComModule {}
