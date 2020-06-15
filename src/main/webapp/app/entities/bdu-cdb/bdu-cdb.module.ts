import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EtatregSharedModule } from 'app/shared/shared.module';
import { BduCdbComponent } from './bdu-cdb.component';
import { BduCdbDetailComponent } from './bdu-cdb-detail.component';
import { BduCdbUpdateComponent } from './bdu-cdb-update.component';
import { BduCdbDeleteDialogComponent } from './bdu-cdb-delete-dialog.component';
import { bduCdbRoute } from './bdu-cdb.route';

@NgModule({
  imports: [EtatregSharedModule, RouterModule.forChild(bduCdbRoute)],
  declarations: [BduCdbComponent, BduCdbDetailComponent, BduCdbUpdateComponent, BduCdbDeleteDialogComponent],
  entryComponents: [BduCdbDeleteDialogComponent],
})
export class EtatregBduCdbModule {}
